import React, { useState } from "react";
import axios from "axios";

function AddProduct() {
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");
  const [weight, setWeight] = useState("");
  const [image, setImage] = useState("");
  const [category, setCategory] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Build product object
    const newProduct = {
      name,
      price: parseInt(price),
      weight: parseFloat(weight),
      image,
      category: { id: parseInt(category) } // assuming backend expects category ID
    };

    try {
      // Send product to backend
      await axios.post("http://localhost:8080/Products/addProduct", newProduct);
      alert("✅ Product added successfully!");

      // Reset form
      setName("");
      setPrice("");
      setWeight("");
      setImage("");
      setCategory("");
    } catch (error) {
      console.error("Error adding product:", error);
      alert("❌ Failed to add product");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        placeholder="Product Name"
        value={name}
        onChange={(e) => setName(e.target.value)}
        required
      />
      <input
        placeholder="Price"
        value={price}
        onChange={(e) => setPrice(e.target.value)}
        required
      />
      <input
        placeholder="Weight (kg)"
        value={weight}
        onChange={(e) => setWeight(e.target.value)}
      />
      <input
        placeholder="Image URL"
        value={image}
        onChange={(e) => setImage(e.target.value)}
      />

      <select
        value={category}
        onChange={(e) => setCategory(e.target.value)}
        required
      >
        <option value="">Select Category</option>
        <option value="1">Electrical</option>
        <option value="2">Home Decoration</option>
        <option value="3">Mens Wear</option>
        <option value="4">Womens Wear</option>
        <option value="5">Mobiles</option>
        <option value="6">Furniture</option>
        <option value="7">Appliances</option>
      </select>

      <button type="submit">Add Product</button>
    </form>
  );
}

export default AddProduct;
