import React from "react";
import { Routes, Route } from "react-router-dom";

import Home from "./pages/Home";
import Login from "./component/Login";
import Signup from "./component/Signup";
import AddProduct from "./pages/AddProduct";
import AddCategory from "./component/AddCategory";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<Signup />} />
      <Route path="/add-product" element={<AddProduct />} />
      <Route path="/add-category" element={<AddCategory />} />
    </Routes>
  );
}

export default App;
