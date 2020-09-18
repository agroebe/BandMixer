import React from 'react';
import NavBar from './components/NavBar'
import Home from './routes/Home'
import About from './routes/About'
import Footer from './components/Footer'
import { BrowserRouter, Route} from 'react-router-dom'

export default function App() {
  return (
    <div className="App">
      <NavBar/>
      <BrowserRouter>
        <Route path='/' exact component={ Home }/>
        <Route path='/about' exact component={ About }/>
      </BrowserRouter>
      {/* <Footer/> */}
    </div>
  );
}