import React from 'react';
import './App.css';
import Dashboard from './components/dashboard/Dashboard';
import FlightList from './components/flights/FlightList';
import PilotList from './components/pilots/PilotList';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Flight Operations Center</h1>
      </header>
      <main className="App-main">
        <Dashboard />
        <div className="App-content">
          <FlightList />
          <PilotList />
        </div>
      </main>
    </div>
  );
}

export default App;
