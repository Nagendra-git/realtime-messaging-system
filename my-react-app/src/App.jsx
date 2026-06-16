import './App.css';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import PublicRoutes from "./routes/public-routes";
import { publicRoutes } from "./routes/routes";

function App() {
  return (
    <Router>
      <Routes>
        {publicRoutes.map((route) => (
          <Route element={<PublicRoutes />} key={route.path}>  {/* ✅ key added */}
            <Route path={route.path} element={route.component} /> {/* ✅ no double-slash */}
          </Route>  
        ))}
      </Routes>
    </Router>
  );
}

export default App;