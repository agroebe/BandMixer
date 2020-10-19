import React from 'react';
import NavBar from './components/NavBar'
import Home from './routes/Home'
import Results from './routes/Results'
import AdminPanel from './admin/AdminPanel'
import { BrowserRouter, Route } from 'react-router-dom'

export default function App() {
  return (
    <div className="App">
      <NavBar/>
      <BrowserRouter>
        <Route path='/' exact component={ Home }/>
        <Route path='/results' exact component={ Results }/>
        <Route path='/admin' exact component={ AdminPanel }/>
      </BrowserRouter>
      {/* <Footer/> */}
    </div>
  );
}