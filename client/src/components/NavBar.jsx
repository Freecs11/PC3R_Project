import React, { useEffect, useState } from "react";
import { AppBar, Box, Toolbar, IconButton, Typography, Menu, Button, MenuItem, Drawer, List, ListItem, ListItemText, Divider } from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import { Link, Routes, Route } from "react-router-dom";
import { AuthData } from "../auth/AuthWrapper";
import LandingPage from "./pages/LandingPage";
import MapPage from "./pages/MapPage";
import MarketPage from "./pages/MarketPage";
import CollectionPage from "./pages/CollectionPage";

const nav = [
    {
        name: "Home",
        path: "/",
        isPrivate: false,
    },
    {
        name: "Map",
        path: "/map",
        isPrivate: true,
    },
    {
        name: "Market",
        path: "/market",
        isPrivate: true,
    },
    {
        name: "Collection",
        path: "/collection",
        isPrivate: true,
    },
];

const LoginButton = () => {
    return (
        <>
            <Button sx={{ color: 'white' }}>
                <Link to="/login" style={{ textDecoration: 'none', color: 'white' }}>
                    Login
                </Link>
            </Button>
            <Button sx={{ color: 'white' }}>
                <Link to="/signup" style={{ textDecoration: 'none', color: 'white' }}>
                    Sign Up
                </Link>
            </Button>
        </>
    );
};

const LogoutButton = ({ coin }) => {
    const { logout } = AuthData();
    return (
        <>
            <Button sx={{ color: 'white' }} onClick={logout}>
                Logout
            </Button>
            <Button sx={{ color: 'white' }} onClick={logout}>
                Coin  : {coin}
            </Button>
        </>
    );
};

const NavBar = () => {
    const { user } = AuthData();
    const [drawerOpen, setDrawerOpen] = useState(false);

    const toggleDrawer = (open) => (event) => {
        if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
            return;
        }
        setDrawerOpen(open);
    };
    useEffect(() => {
        console.log(user);
    }, [user]);

    const drawerList = (
        <Box
            sx={{ width: 250 }}
            role="presentation"
            onClick={toggleDrawer(false)}
            onKeyDown={toggleDrawer(false)}
        >
            <List>
                {nav.map((page) => (
                    <ListItem button key={page.name}>
                        <Link to={page.path} style={{ textDecoration: 'none', color: 'inherit' }}>
                            <ListItemText primary={page.name} />
                        </Link>
                    </ListItem>
                ))}
            </List>
            <Divider />
            <List>
                {user.isAuthenticated ? (
                    <>
                        <ListItem button>
                            <LogoutButton />
                        </ListItem>
                    </>
                ) : (
                    <>
                        <ListItem button>
                            <LoginButton />
                        </ListItem>
                    </>
                )}
            </List>
        </Box>
    );

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar>
                    <IconButton
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        sx={{ mr: 2, display: { md: 'none' } }}
                        onClick={toggleDrawer(true)}
                    >
                        <MenuIcon />
                    </IconButton>
                    <Drawer
                        anchor="left"
                        open={drawerOpen}
                        onClose={toggleDrawer(false)}
                    >
                        {drawerList}
                    </Drawer>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        VFarm
                    </Typography>
                    <Box sx={{ display: { xs: 'none', md: 'flex' } }}>
                        {nav.map((page) => (
                            <Button key={page.name} sx={{ color: 'white' }}>
                                <Link to={page.path} style={{ textDecoration: 'none', color: 'white' }}>
                                    {page.name}
                                </Link>
                            </Button>
                        ))}
                        {user.isAuthenticated ? <LogoutButton coin={user.coin} /> : <LoginButton />}
                    </Box>
                </Toolbar>
            </AppBar>
        </Box>
    );
};

export default NavBar;