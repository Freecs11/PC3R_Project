import { createTheme, ThemeProvider } from "@mui/material/styles";
import { useCallback, useState, useEffect } from "react";
import Container from "@mui/material/Container";
import CssBaseline from "@mui/material/CssBaseline"
import Button from "@mui/material/Button";
import axios from "axios";
import { AuthData } from "../../auth/AuthWrapper"
import { Avatar, Box, Modal, Typography, Grid } from "@mui/material";
import Particles from "react-particles";
import { loadFireworksPreset } from "tsparticles-preset-fireworks";


import { MapContainer, TileLayer, useMap, Marker, Popup } from 'react-leaflet'
import { CardProfile } from "../CardProfile";

const style = {
  width: '100%',
  maxWidth: 360,
  bgcolor: 'background.paper',
  boxShadow: 3,
  borderRadius: 2,
  p: 2,
  mt: 15,
  mx: 'auto', // Center the box horizontally
  textAlign: 'center', // Center align text
};
const style2 = {
  width: '100%',
  maxWidth: 1280,
  maxHeight: 720,
  bgcolor: 'background.paper',
  boxShadow: 3,
  borderRadius: 2,
  p: 2,
  mt: 10,
  mx: 'auto', // Center the box horizontally
  textAlign: 'center', // Center align text
};




function MapPage() {
  const { user } = AuthData();
  const [dungeons, setDungeons] = useState([]);
  const position = user.position;
  const getPositionAPI = useCallback(async () => {
    try {
      axios(import.meta.env.VITE_API_URL + "api/v1/dungeons?posX=" + position.latitude + "&posY=" + position.longitude,
        {
          method: "GET",
          withCredentials: true
        }).then((response) => {
          const dungeons = JSON.parse(response.data.message);
          console.log(dungeons);
          setDungeons(dungeons);
        });
    }
    catch (error) {
      console.error("There was an error!", error);
    }
  }, []);

  useEffect(() => {
    console.log(user);
    getPositionAPI();
    const interval = setInterval(() => {
      getPositionAPI();
    }, 3600000 * 6);
    console.log(dungeons);
    return () => clearInterval(interval);

  }, [getPositionAPI]);


  if (!user.isAuthenticated) {
    window.location.href = "/login";
  }
  const defaultTheme = createTheme();
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const [openPet, setOpenPet] = useState(false);
  const handleOpenPet = () => setOpenPet(true);
  const handleClosePet = () => setOpenPet(false);
  const [selectedPet, setSelectedPet] = useState([]);

  // Define the state to manage the toggle effect
  const [isToggled, setIsToggled] = useState(false);

  // Toggle handler
  const handleToggle = () => {
    setIsToggled(!isToggled);
  };
  useEffect(() => {
    console.log(selectedPet);
    console.log(selectedPet.length);
  }, [selectedPet]);
  return (
    <ThemeProvider theme={defaultTheme}>
      <Container component="main">
        <CssBaseline />
        <div>
          <MapContainer center={
            [position.latitude, position.longitude]
          } zoom={12} scrollWheelZoom={false}>
            <TileLayer
              url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
              attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
            />
            {dungeons.map((dungeon) => (<>
              <Marker eventHandlers={{ click: handleOpen }} key={dungeon.id} position={[dungeon.localX, dungeon.localY]}>

              </Marker>
              <Modal
                key={dungeon.id + "modal"}
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
                    {dungeon.name}
                  </Typography>
                  <Avatar alt="Remy Sharp" src="https://minecraft.wiki/images/Broken_Citadel.png?f6417" sx={{ width: 100, height: 100, mx: 'auto', mt: 2 }} />
                  <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                    Status: {dungeon.status}
                  </Typography>
                  <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                    Type: {dungeon.type}
                  </Typography>
                  {dungeon.status == "idle" ? <Button
                    sx={{ mt: 3 }}
                    onClick={() => {
                      try {
                        axios.get(
                          import.meta.env.VITE_API_URL + "api/v1/DungeonService/" + dungeon.id + "/fight?userId=" + user.userID,
                          {
                            withCredentials: true
                          }
                        ).then((response) => {
                          if (response.data.status == "success") {
                            console.log(response);
                            handleOpenPet();
                            handleClose();
                          }
                        })
                      }
                      catch (error) {
                        console.error("There was an error!", error);
                      }
                    }}
                    variant="contained"
                    color="primary"
                  >
                    Enter
                  </Button> : <Button
                    sx={{ mt: 3 }}
                    variant="contained"
                    color="primary"
                    disabled
                  >
                    Enter
                  </Button>}

                </Box>
              </Modal>

              <Modal
                key={"petselect"}
                open={openPet}
                onClose={handleClosePet}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
                BackdropProps={{
                  style: { backgroundColor: 'rgba(0, 0, 0, 0.2)' },
                }
                }>

                <Box sx={style2}>
                  <Grid container spacing={3}>
                    {user.collection.map((pet) => (
                      <Grid item xs={12} sm={6} md={4} key={pet.id} sx={{ p: 2 }}>

                        <CardProfile
                          image="https://source.unsplash.com/random"
                          name={pet.name}
                          type={pet.type}
                          price={pet.price}
                          purchasedAt={pet.purchasedAt}
                          createdAt={pet.createdAt}
                          health={pet.health}
                        />

                        {
                          selectedPet.includes(pet.id) ? <Button onClick={() => {
                            setSelectedPet(selectedPet.filter((item) => item !== pet.id));
                          }
                          }>
                            Remove
                          </Button> : <Button onClick={() => {
                            selectedPet.length < 3 ? setSelectedPet([...selectedPet, pet.id]) : alert("You can only select 4 pets");
                          }
                          }>
                            Select
                          </Button>

                        }
                      </Grid>
                    ))
                    }
                  </Grid>
                  <Button onClick={() => {
                    try {
                      axios.get(
                        import.meta.env.VITE_API_URL + "api/v1/DungeonService/" + dungeon.id + "/reset?userId=" + user.userID,
                        {
                          withCredentials: true
                        }
                      ).then((response) => {
                        if (response.data.status == "success") {
                          console.log(response);
                          handleClosePet();
                          handleClose();
                        }
                      })
                    }
                    catch (error) {
                      console.error("There was an error!", error);
                    }
                  }
                  }>
                    Reset Dungeon
                  </Button>

                  {selectedPet.length == 3 ? <Button onClick={() => {
                    try {
                      axios(import.meta.env.VITE_API_URL + "api/v1/DungeonService/" + dungeon.id + "/combat",
                        {
                          method: "POST",
                          withCredentials: true,
                          data: {
                            userId: user.userID,
                            selectedPets: selectedPet
                          }
                        }).then((response) => {
                          if (response.data.status == "success") {
                            console.log(response);
                            alert(response.data.message);
                            handleClosePet();
                            handleClose();
                          }
                        }
                        )
                    }
                    catch (error) {
                      console.error("There was an error!", error);
                    }
                  }
                  }>
                    Fight
                  </Button>

                    : <Button onClick={() => {
                      alert("You must select 3 pets");
                    }
                    }>
                      Fight
                    </Button>}
                </Box>
              </Modal>

            </>
            ))}

          </MapContainer>

        </div>
      </Container>
    </ThemeProvider >
  );
}

export default MapPage;
