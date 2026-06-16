import { useState, useEffect } from "react";
import { Client } from "@stomp/stompjs";
import "./Home.css"; // ✅ import CSS file

function StatusBadge({ status }) {
  const classMap = {
    Completed: "status-completed",
    Progress:  "status-progress",
    Pending:   "status-pending",
    Failed:    "status-failed",
  };
  const badgeClass = classMap[status] || "status-pending";

  return (
    <span className={`status-badge ${badgeClass}`}>
      {status}
    </span>
  );
}

function Home() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [connected, setConnected] = useState(false);



  // ✅ REST fetch
  useEffect(() => {
    fetch("http://localhost:8080/status")
      .then((response) => {
        if (!response.ok) throw new Error("Failed to fetch data");
        return response.json();
      })
      .then((data) => {
        setData(data);
        setLoading(false);
      })
      .catch((error) => {
        setError(error.message);
        setLoading(false);
      });
  }, []);

  // ✅ WebSocket
  useEffect(() => {
    const client = new Client({
      brokerURL: "ws://localhost:8080/ws",
      onConnect: () => {
        setConnected(true);
        client.subscribe("/topic/ai-status", (message) => {
          const updatedStatus = JSON.parse(message.body);
          setData((prevData) =>
            prevData.map((item) =>
              item.id === updatedStatus.id ? updatedStatus : item
            )
          );
        });
      },
      onDisconnect: () => setConnected(false),
      onStompError: (frame) => console.error("WebSocket Error:", frame),
    });

    client.activate();
    return () => client.deactivate();
  }, []);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;

  return (
    <div className="container">
      <div className="header">
        <h2 className="title">AI Generated Status</h2>
        <span className={`badge ${connected ? "badge-connected" : "badge-disconnected"}`}>
          <span className={`dot ${connected ? "dot-connected" : "dot-disconnected"}`} />
          {connected ? "Connected" : "Disconnected"}
        </span>
      </div>

      <div className="table-wrap">
        <table>
          <thead>
            <tr>
              <th>Job ID</th>
              <th>Title</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {data.map((item) => (
              <tr key={item.id}>
                <td className="id-cell">{item.id}</td>
                <td>{item.title}</td>
                <td><StatusBadge status={item.status} /></td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default Home;