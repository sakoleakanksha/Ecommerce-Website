import React, { useState, useEffect } from "react";
import axios from "axios";

export default function AddCategory() {
  const [category, setCategory] = useState("");
  const [categories, setCategories] = useState([]);

  // Fetch all categories
  const fetchCategories = async () => {
    try {
      const res = await axios.get("http://localhost:8080/categories/getAll");
      setCategories(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  // Add a new category
  const handleAdd = async (e) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:8080/categories/add", { category });
      alert("✅ Category added successfully!");
      setCategory("");
      fetchCategories();
    } catch (err) {
      console.error(err);
      alert("❌ Failed to add category");
    }
  };

  useEffect(() => {
    fetchCategories();
  }, []);

  return (
    <div style={{ maxWidth: "500px", margin: "50px auto" }}>
      <h2>Add Category</h2>
      <form onSubmit={handleAdd}>
        <input
          type="text"
          placeholder="Enter category name (e.g. Electronics)"
          value={category}
          onChange={(e) => setCategory(e.target.value)}
          required
          style={{ width: "100%", padding: "8px", marginBottom: "10px" }}
        />
        <button type="submit" style={{ width: "100%", padding: "10px" }}>
          Add Category
        </button>
      </form>

      <h3 style={{ marginTop: "30px" }}>Existing Categories</h3>
      {categories.length === 0 ? (
        <p>No categories yet.</p>
      ) : (
        <ul>
          {categories.map((c) => (
            <li key={c.id}>{c.category}</li>
          ))}
        </ul>
      )}
    </div>
  );
}
