import React from "react";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import Container from "@mui/material/Container";
import CssBaseline from "@mui/material/CssBaseline";
import { Typography, Box } from "@mui/material";
import { AuthData } from "../../auth/AuthWrapper";
function LandingPage() {
  const { user } = AuthData();
  const defaultTheme = createTheme();

  return (
    <ThemeProvider theme={defaultTheme}>
      <CssBaseline />
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          justifyContent: "center",
          height: "100vh",
          backgroundImage: "url(https://i.imgur.com/qCMIDl6.png)",
          backgroundSize: "cover",
          backgroundPosition: "center",
          textAlign: "center",
          color: "white",
          padding: 2,
          boxShadow: "inset 0 0 0 2000px rgba(0, 0, 0, 0.5)",
        }}
      >
        <Typography variant="h1" component="h1" gutterBottom>
          Welcome to VFarm!
        </Typography>
        <Typography variant="h5" component="h5" gutterBottom>
          A virtual farming experience
        </Typography>
        <Typography variant="body1" component="p" gutterBottom>
          Send your pets to dungeons and watch them die
        </Typography>
      </Box>
    </ThemeProvider>
  );
}

export default LandingPage;