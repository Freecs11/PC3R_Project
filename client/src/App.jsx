import "./App.css";
import { BrowserRouter } from "react-router-dom";
import NavBar from "./components/NavBar";
import LoginPage from "./components/pages/LoginPage";
import { Routes, Route } from "react-router-dom";
import SignupPage from "./components/pages/SignupPage";
import LandingPage from "./components/pages/LandingPage";
import MarketPage from "./components/pages/MarketPage";
import CollectionPage from "./components/pages/CollectionPage";
import MapPage from "./components/pages/MapPage";
import { AuthWrapper } from "./auth/AuthWrapper";

function App() {

  return (
    <>

      <AuthWrapper>
        <NavBar />
        <div>
          <Routes>
            <Route path="/login" element={<LoginPage />} />
            <Route path="/signup" element={<SignupPage />} />
            <Route path="/" element={<LandingPage />} />
            <Route path="/market" element={<MarketPage />} />
            <Route path="/collection" element={<CollectionPage />} />
            <Route path="/map" element={<MapPage />} />
            <Route path="*" element={<LandingPage />} />
          </Routes>
        </div>
      </AuthWrapper>
    </>
  );
}

export default App;
