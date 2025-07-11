// src/App.jsx
import React from "react";
import { Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Create from "./components/Create";
import Edit from "./components/Edit";
import AllPosts from "./components/AllPosts";

const App = () => {
  return (
    <>
      <Navbar />
      <Routes>
        <Route path="/" element={<AllPosts />} />
        <Route path="/create" element={<Create />} />
        <Route path="/edit" element={<Edit />} />
      </Routes>
    </>
  );
};

export default App;
