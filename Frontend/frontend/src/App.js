import React from 'react';
import NavBar from './components/NavBar'
import Home from './routes/Home'
import Results from './routes/Results'
import Explore from './routes/Explore'
import AdminPanel from './admin/AdminPanel'
import NotFoundPage from './routes/NotFoundPage'
import Profile from './routes/Profile.js'
import EditProfile from './routes/EditProfile.js'
import Footer from './components/Footer'
import { BrowserRouter, Route } from 'react-router-dom'

export default function App() {
  return (
    <div className="App">
      <NavBar/>
      <BrowserRouter>
        <Route path='/' exact component={ Home }/>
        <Route path='/results' exact component={ Results }/>
        <Route path='/explore' exact component={ Explore }/>
        <Route path='/profile' exact component={ Profile }/>
        <Route path='/editprofile' exact component={ EditProfile }/>
        <Route path='/admin' exact component={ AdminPanel }/>

        <Route path='/california' exact component={withProps(Home, { loc: 'california' })}/>
        <Route path='/florida' exact component={withProps(Home, { loc: 'florida' })}/>
        <Route path='/georgia' exact component={withProps(Home, { loc: 'georgia' })}/>
        <Route path='/illinois' exact component={withProps(Home, { loc: 'illinois' })}/>
        <Route path='/iowa' exact component={withProps(Home, { loc: 'iowa' })}/>
        <Route path='/michigan' exact component={withProps(Home, { loc: 'michigan' })}/>
        <Route path='/minnesota' exact component={withProps(Home, { loc: 'minnesota' })}/>
        <Route path='/new-jersey' exact component={withProps(Home, { loc: 'new jersey' })}/>
        <Route path='/new-york' exact component={withProps(Home, { loc: 'new york' })}/>
        <Route path='/pennsylvania' exact component={withProps(Home, { loc: 'pennsylvania' })}/>
        <Route path='/texas' exact component={withProps(Home, { loc: 'texas' })}/>
        <Route path='/washington' exact component={withProps(Home, { loc: 'washington' })}/>

        <Route path="/n" component={ NotFoundPage } />

      </BrowserRouter>
      <Footer/>
    </div>
  );
}

function withProps(Component, props) {
  return function(matchProps) {
    return <Component {...props} {...matchProps} />
  }
}