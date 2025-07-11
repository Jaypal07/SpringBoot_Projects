import React, { useEffect, useState } from "react";
import { Typography, TextField, Button, Paper, Box } from "@mui/material";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";

const initial = {
  postId: "",
  postProfile: "",
  reqExperience: 0,
  postTechStack: [],
  postDesc: "",
};

const skillSet = ["Javascript", "Java", "Python", "Django", "Rust"];

const Edit = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const [form, setForm] = useState(initial);
  const currId = location.state?.id;

  useEffect(() => {
    if (!currId) return;
    const fetchPost = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/jobPost/${currId}`
        );
        setForm(response.data);
      } catch (error) {
        console.error("Error fetching post:", error);
      }
    };
    fetchPost();
  }, [currId]);

  // Handle changes in text fields (except skills)
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]: name === "reqExperience" ? Number(value) : value,
    }));
  };

  // Handle skill checkbox toggle
  const handleSkillChange = (e) => {
    const skill = e.target.value;
    if (e.target.checked) {
      // Add skill if checked
      setForm((prev) => ({
        ...prev,
        postTechStack: [...prev.postTechStack, skill],
      }));
    } else {
      // Remove skill if unchecked
      setForm((prev) => ({
        ...prev,
        postTechStack: prev.postTechStack.filter((s) => s !== skill),
      }));
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`http://localhost:8080/jobPost/${form.postId}`, form);
      navigate("/"); // navigate back after successful update
    } catch (error) {
      console.error("Failed to update post:", error);
    }
  };

  return (
    <Paper sx={{ padding: "1%" }} elevation={0}>
      <Typography sx={{ margin: "3% auto" }} align="center" variant="h5">
        Edit Job Post
      </Typography>
      <form autoComplete="off" noValidate onSubmit={handleSubmit}>
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <TextField
            name="postId"
            type="number"
            sx={{ width: "50%", margin: "1% auto" }}
            label="Post ID"
            variant="outlined"
            value={form.postId}
            disabled
          />
          <TextField
            name="postProfile"
            type="text"
            sx={{ width: "50%", margin: "1% auto" }}
            required
            label="Job Profile"
            variant="outlined"
            value={form.postProfile}
            onChange={handleInputChange}
          />
          <TextField
            name="reqExperience"
            type="number"
            sx={{ width: "50%", margin: "1% auto" }}
            required
            label="Years of Experience"
            variant="outlined"
            value={form.reqExperience}
            onChange={handleInputChange}
            inputProps={{ min: 0 }}
          />
          <TextField
            name="postDesc"
            type="text"
            sx={{ width: "50%", margin: "1% auto" }}
            required
            multiline
            rows={4}
            label="Job Description"
            variant="outlined"
            value={form.postDesc}
            onChange={handleInputChange}
          />
          <Box sx={{ width: "50%", margin: "1% auto" }}>
            <Typography variant="h6">Required Skills</Typography>
            {skillSet.map((skill, index) => (
              <Box key={index} sx={{ display: "flex", alignItems: "center" }}>
                <input
                  type="checkbox"
                  id={`checkbox-${index}`}
                  value={skill}
                  checked={form.postTechStack.includes(skill)}
                  onChange={handleSkillChange}
                />
                <label htmlFor={`checkbox-${index}`} style={{ marginLeft: 8 }}>
                  {skill}
                </label>
              </Box>
            ))}
          </Box>
          <Button
            sx={{ width: "50%", margin: "2% auto" }}
            variant="contained"
            type="submit"
          >
            Update Post
          </Button>
        </Box>
      </form>
    </Paper>
  );
};

export default Edit;
