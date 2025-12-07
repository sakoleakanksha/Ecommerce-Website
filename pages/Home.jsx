import React from "react";
import { Link } from "react-router-dom";

const Home = () => {
  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h1>Welcome to My E-commerce Store</h1>
      <p>Find the best products at the best prices!</p>

      <div style={{ marginTop: "30px" }}>
        <Link to="/">
          <button style={{ padding: "10px 20px", marginRight: "10px" }}>Login</button>
        </Link>
        <Link to="/signup">
          <button style={{ padding: "10px 20px" }}>Signup</button>
        </Link>
      </div>
    </div>
  );
};

export default Home;
