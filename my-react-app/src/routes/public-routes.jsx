import React from 'react';
import { Outlet } from 'react-router-dom'; // ✅ Outlet renders the matched child route

const PublicRoutes = () => {
  return <Outlet />; // ✅ was incorrectly returning Navigate class reference
};

export default PublicRoutes;