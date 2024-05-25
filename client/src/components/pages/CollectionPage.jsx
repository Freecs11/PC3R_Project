import { useEffect, useState } from "react";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import Container from "@mui/material/Container";
import CssBaseline from "@mui/material/CssBaseline"
import { AuthData } from "../../auth/AuthWrapper";
import { useNavigate } from "react-router-dom";
import { Box, Grid, Button } from "@mui/material";
import axios from "axios";
import { CardProfile } from "../CardProfile";
import Modal from "@mui/material/Modal";
import Typography from "@mui/material/Typography";

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
};


function CollectionPage() {
  const { user, setUser } = AuthData();
  const navigate = useNavigate();
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);


  if (!user.isAuthenticated) {
    navigate("/login");
  }
  const [collection, setCollection] = useState([]);
  useEffect(() => {
    axios.get(import.meta.env.VITE_API_URL
      + "api/v1/pet?ownerId=" + user.userID,
      {
        withCredentials: true
      }
    ).then((response) => {
      console.log(response.data);
      setCollection(response.data);
      setUser({ ...user, collection: response.data });
    });
  }, [user.userID]);
  const defaultTheme = createTheme();
  return (
    <ThemeProvider theme={defaultTheme}>
      <Container component="main">
        <CssBaseline />
        <Box
          sx={{ flexGrow: 1, p: 2 }}>
          <Grid container spacing={3}>
            {collection.map((pet) => (
              <Grid item xs={12} sm={6} md={4} key={pet.id} sx={{ p: 2 }}>
                <div onClick={handleOpen}> <CardProfile
                  image="https://source.unsplash.com/random"
                  name={pet.name}
                  type={pet.type}
                  price={pet.price}
                  purchasedAt={pet.purchasedAt}
                  createdAt={pet.createdAt}
                  health={pet.health}
                />
                </div>
                <Modal
                  open={open}
                  onClose={handleClose}
                  aria-labelledby="modal-modal-title"
                  aria-describedby="modal-modal-description"
                  BackdropProps={{
                    style: { backgroundColor: 'rgba(0, 0, 0, 0.2)' },
                  }}
                >
                  <Box sx={style}>
                    <Typography id="modal-modal-title" variant="h6" component="h2">
                      {pet.name}
                    </Typography>
                    <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                      <Typography> Type: {pet.type}</Typography>
                      <Typography> Price: {pet.price}</Typography>
                      <Typography> Purchased At: {pet.purchasedAt}</Typography>
                      <Typography> Created At: {pet.createdAt}</Typography>
                      <Typography> Health: {pet.health}</Typography>
                    </Typography>
                    <Button onClick={() => {
                      try {
                        axios(import.meta.env.VITE_API_URL
                          + "api/v1/market?sellerId=" + user.userID + "&petId=" + pet.id, {
                          method: "POST",
                          withCredentials: true
                        }
                        ).then((response) => {
                          console.log(response.data);
                          const newCollection = collection.filter((item) => item.id !== pet.id);
                          setCollection(newCollection);
                          setUser({ ...user, collection: newCollection, coin: user.coin + pet.price });
                          handleClose();
                        });
                      }
                      catch (error) {
                        console.error("There was an error!", error);
                      }
                    }
                    }>
                      Sell
                    </Button>
                  </Box>
                </Modal>
              </Grid>
            ))}
          </Grid>
        </Box>
      </Container>
    </ThemeProvider>
  );
}

export default CollectionPage;
