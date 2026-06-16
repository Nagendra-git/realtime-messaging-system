import Home from "../components/home/Home";

  const publicRoutes = [
    {
      path: "home",
      component: <Home />,
      exact: true,
    },
    
  ];

  export { publicRoutes };