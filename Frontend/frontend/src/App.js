import React from 'react';
import NavBar from './components/NavBar'
import Chat from './routes/Chat'
import Home from './routes/Home'
import Results from './routes/Results'
import Explore from './routes/Explore'
import Post from './routes/Post'
import User from './routes/User'
import AdminPanel from './admin/AdminPanel'
import NotFoundPage from './routes/NotFoundPage'
import Footer from './components/Footer'
import { BrowserRouter, Route, Switch } from 'react-router-dom'
import Users from './routes/Users';

export default function App() {
  return (
    <div className="App">
      <NavBar/>
      <BrowserRouter>
        <Switch>
          <Route path='/' exact component={ Home }/>
          <Route path='/results' exact component={ Results }/>
          <Route path='/explore' exact component={ Explore }/>
          <Route path='/users' exact component={ Users }/>
          <Route path='/admin' exact component={ AdminPanel }/>
          <Route path='/chat' exact component={ Chat }/>
          <Route path='/post/:postId' exact component={ Post }/>
          <Route path='/user/:userId' exact component={ User }/>

          <Route path="" component={ NotFoundPage } />
        </Switch>

      </BrowserRouter>
      <Footer/>
    </div>
  );
}