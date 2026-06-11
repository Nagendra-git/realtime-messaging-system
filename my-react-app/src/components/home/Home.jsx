import { useState, useEffect } from "react";
import { Client } from "@stomp/stompjs"; // ✅ only this import needed

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

  // ✅ WebSocket - no SockJS
  useEffect(() => {
    const client = new Client({
      brokerURL: "ws://localhost:8080/ws", // ✅ ws:// instead of http://
      onConnect: () => {
        setConnected(true);
        console.log("WebSocket Connected");

        client.subscribe("/topic/ai-status", (message) => {
          const updatedStatus = JSON.parse(message.body);
          console.log("WS update received:", updatedStatus);

          setData((prevData) =>
            prevData.map((item) =>
              item.id === updatedStatus.id ? updatedStatus : item
            )
          );
        });
      },
      onDisconnect: () => {
        setConnected(false);
        console.log("WebSocket Disconnected");
      },
      onStompError: (frame) => {
        console.error("WebSocket Error:", frame);
      },
    });

    client.activate();

    return () => client.deactivate();
  }, []);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;

  return (
    <div>
      <p>WebSocket: {connected ? "🟢 Connected" : "🔴 Disconnected"}</p>
      <table border="1">
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
              <td>{item.id}</td>
              <td>{item.title}</td>
              <td>{item.status}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Home;