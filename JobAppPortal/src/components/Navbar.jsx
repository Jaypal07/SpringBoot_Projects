import React from "react";
import { AppBar, Toolbar, Box, Grid, Typography, Button } from "@mui/material";

const Navbar = () => {
  return (
    <div>
      <Grid container spacing={2} sx={{ margin: "2%" }}>
        <Box sx={{ flexGrow: 1 }}>
          <AppBar position="static" style={{ background: "#ADD8E6" }}>
            <Toolbar variant="dense">
              <Typography
                variant="h4"
                align="left"
                component="div"
                sx={{
                  flexGrow: 1,
                  fontFamily: "revert",
                  fontWeight: 500, // Fixed: fontWeight is a number here
                  fontSize: "1.5rem", // Fixed: fontSize as valid CSS value
                  color: "black",
                }}
              >
                Job Portal
              </Typography>

              <Box sx={{ m: 0.5, mx: "auto", width: 80 }}>
                <Button variant="outlined" href="http://localhost:3000">
                  Home
                </Button>
              </Box>

              <Box sx={{ m: 0.5, mx: "auto", width: 100 }}>
                <Button variant="outlined" href="http://localhost:3000/create">
                  Add Job
                </Button>
              </Box>

              <Box sx={{ m: 0.5, mx: "auto", width: 180 }}>
                <Button variant="outlined" href="https://telusko.com/">
                  Contact Us
                </Button>
              </Box>
            </Toolbar>
          </AppBar>
        </Box>
      </Grid>

      {/* Fixed sx prop here - removed invalid sx={12} */}
      <Grid item xs={12} md={12} lg={12}></Grid>
    </div>
  );
};

export default Navbar;
