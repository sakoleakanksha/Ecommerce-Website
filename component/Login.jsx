import React, { useState } from "react";
import axios from "axios";

function Login() {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  
  // FIX: Add this state
  const [error, setError] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const res = await axios.post("http://localhost:8080/login", {
        email,
        password,
      });

      alert("Login successful!");
      console.log(res.data);

    } catch (err) {
      const msg = err.response?.data?.message || "Invalid credentials";
      setError(msg); // <--- FIX
    }
  };

  return (
    <div>
      <h2>Login</h2>

      {/* Show error */}
      {error && <p style={{ color: "red" }}>{error}</p>}

      <form onSubmit={handleLogin}>
        <input
          type="email"
          placeholder="Enter Email"
          onChange={(e) => setEmail(e.target.value)}
        />

        <input
          type="password"
          placeholder="Enter Password"
          onChange={(e) => setPassword(e.target.value)}
        />

        <button type="submit">Login</button>
      </form>
    </div>
  );
}

export default Login;
