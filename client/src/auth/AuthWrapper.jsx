import { createContext, useContext, useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Cookies from 'universal-cookie';
import axios from "axios";
const AuthContext = createContext();
export const AuthData = () => useContext(AuthContext);

export const AuthWrapper = ({ children }) => {

    const [user, setUser] = useState({
        name: "",
        userID: "",
        isAuthenticated: false,
        collection: [],
        coin: 0,
        position: { latitude: 48.866667, longitude: 2.333333 }
    })


    useEffect(() => {

        navigator.geolocation.getCurrentPosition(function (position) {
            setUser({ ...user, position: { latitude: position.coords.latitude, longitude: position.coords.longitude } });
        });

    }, [user.userID]);

    const navigate = useNavigate();

    const login = (data) => {
        const url =
            import.meta.env.VITE_API_URL + "login?username=" +
            data.get("username") +
            "&password=" +
            data.get("password");
        try {
            axios.post(url,
                { withCredentials: true }
            ).then((response) => {
                if (response.status === 200) {
                    const messageData = response.data.message;
                    const userData = JSON.parse(messageData);
                    setUser({
                        ...user,
                        name: userData.username,
                        userID: userData.userId,
                        isAuthenticated: true,
                        coin: userData.coin
                    });
                    const cookies = new Cookies();
                    const sessionID = userData.JSESSIONID
                    cookies.set("JSESSIONID", sessionID, { path: "/" })
                    console.log(response);
                    navigate("/collection");
                } else {
                    alert("Invalid username or password");
                }
            });
        } catch (error) {
            console.error("There was an error!", error);
        }
    }

    const logout = () => {
        axios.post(import.meta.env.VITE_API_URL
            + "logout");
        setUser({ name: "", isAuthenticated: false });
        navigate("/");
    }


    return (

        <AuthContext.Provider value={{ setUser, user, login, logout }}>
            <>
                {children}
            </>

        </AuthContext.Provider>

    )

}