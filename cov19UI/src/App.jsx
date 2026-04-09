import './App.css'
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Login from './authentication_ui/login';
import Signup from './authentication_ui/signup';
import HomePage from './home/homepage';
import CovidDashboard from './covidDashboard/covidDash';

function App() {

  return (
    <Router>
      <div>
          <Routes>
            <Route path="/login" element={<Login/>} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/" element={<HomePage />} />
            <Route path='/covid' element={<CovidDashboard />} />
          </Routes>
      </div>
    </Router>
  )
}

export default App;
