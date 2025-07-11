import React, { useEffect, useState } from "react";
import { Card, Grid, Typography, TextField, Button, Box } from "@mui/material";
import DeleteIcon from "@mui/icons-material/Delete";
import EditIcon from "@mui/icons-material/Edit";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Search = () => {
  const [posts, setPosts] = useState([]);
  const [searchKeyword, setSearchKeyword] = useState("");
  const navigate = useNavigate();

  const fetchAllPosts = async () => {
    try {
      const response = await axios.get("http://localhost:8080/jobPosts");
      setPosts(response.data);
    } catch (error) {
      console.error("Error fetching posts:", error);
    }
  };

  const handleSearch = async () => {
    if (!searchKeyword.trim()) {
      fetchAllPosts();
      return;
    }

    try {
      const response = await axios.get(
        `http://localhost:8080/keyword/${searchKeyword}`
      );
      setPosts(response.data);
    } catch (error) {
      console.error("Error searching posts:", error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/jobPost/${id}`);
      setPosts((prevPosts) => prevPosts.filter((post) => post.postId !== id));
      // navigate("/");
      alert("Post deleted successfully");
      fetchAllPosts(); // Refresh the posts after deletion
    } catch (error) {
      console.error("Failed to delete post:", error);
    }
  };

  const handleEdit = (id) => {
    navigate("/edit", { state: { id } });
  };

  useEffect(() => {
    fetchAllPosts();
  }, []);

  return (
    <>
      <Box
        sx={{
          display: "flex",
          gap: 2,
          justifyContent: "center",
          marginTop: 4,
          marginBottom: 2,
        }}
      >
        <TextField
          label="Search Job Posts"
          // variant="outlined"
          value={searchKeyword}
          onChange={(e) => setSearchKeyword(e.target.value)}
        />
        <Button variant="contained" color="primary" onClick={handleSearch}>
          Search
        </Button>
      </Box>

      <Grid container spacing={2} sx={{ margin: "2%" }}>
        {posts.map((p) => (
          <Grid key={p.postId} item xs={12} md={6} lg={4}>
            <Card
              sx={{
                padding: "3%",
                overflow: "hidden",
                width: "84%",
                backgroundColor: "#ADD8E6",
              }}
            >
              <Typography
                variant="h5"
                sx={{
                  fontSize: "2rem",
                  fontWeight: 600,
                  fontFamily: "sans-serif",
                }}
              >
                {p.postProfile}
              </Typography>

              <Typography
                sx={{
                  color: "#585858",
                  marginTop: "2%",
                  fontFamily: "cursive",
                }}
                variant="body1"
              >
                Description: {p.postDesc}
              </Typography>

              <br />
              <Typography
                variant="h6"
                sx={{ fontFamily: "unset", fontSize: "1rem" }}
              >
                Experience: {p.reqExperience} years
              </Typography>

              <Typography
                sx={{ fontFamily: "serif", fontSize: "1rem" }}
                gutterBottom
                variant="body1"
              >
                Skills:
              </Typography>

              {p.postTechStack.map((s, i) => (
                <Typography variant="body1" gutterBottom key={i}>
                  {s} .{" "}
                </Typography>
              ))}

              <Box sx={{ marginTop: 1 }}>
                <DeleteIcon
                  sx={{ cursor: "pointer", marginRight: 1 }}
                  onClick={() => handleDelete(p.postId)}
                />
                <EditIcon
                  sx={{ cursor: "pointer" }}
                  onClick={() => handleEdit(p.postId)}
                />
              </Box>
            </Card>
          </Grid>
        ))}
      </Grid>
    </>
  );
};

export default Search;
