import './App.css'
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from './authentication_ui/login';
import Signup from './authentication_ui/signup';
import HomePage from './home/homepage';
import CovidDashboard from './covidDashboard/covidDash';
import HelpDesk from './helpdesk/helpdesk';
import AdminSignup from './authentication_ui/adminsignup';
import ProtectedRoutes from './components/protectroutes';

function App() {

  return (
    <Router>
      <div>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/adminsignup" element={<AdminSignup />} />
          <Route path="/" element={<HomePage />} />
          <Route path='/helpdesk' element={<HelpDesk />} />
          <Route element={<ProtectedRoutes />}>
            <Route path='/covid' element={<CovidDashboard />} />
          </Route>
        </Routes>
      </div>
    </Router>
  )
}

export default App;
