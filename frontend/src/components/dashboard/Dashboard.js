import React, { useState, useEffect } from 'react';
import { FlightService, PilotService } from '../../services/api';
import './styles/Dashboard.css';

const Dashboard = () => {
  const [flights, setFlights] = useState([]);
  const [pilots, setPilots] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [flightData, pilotData] = await Promise.all([
          FlightService.getAllFlights(),
          PilotService.getAllPilots()
        ]);
        setFlights(flightData);
        setPilots(pilotData);
        setLoading(false);
      } catch (error) {
        setError('Failed to fetch data');
        setLoading(false);
      }
    };
    fetchData();
  }, []);

  if (loading) return <div className="dashboard-loading">Loading...</div>;
  if (error) return <div className="dashboard-error">{error}</div>;

  return (
    <div className="dashboard">
      <header className="dashboard-header">
        <h1>Flight Operations Center</h1>
        <p className="dashboard-subtitle">Real-time Flight and Pilot Management</p>
      </header>
      <main className="dashboard-content">
        <section className="flights-section">
          <div className="section-header">
            <h2>Active Flights</h2>
            <span className="flight-count">{flights.length} Flights</span>
          </div>
          <div className="flight-list">
            {flights.map(flight => (
              <div key={flight.id} className="flight-card">
                <div className="flight-header">
                  <h3>{flight.flightNumber}</h3>
                  <span className="status">Scheduled</span>
                </div>
                <div className="flight-details">
                  <p className="route">{flight.origin} → {flight.destination}</p>
                  <p className="time">
                    <span>Departure: {new Date(flight.departureTime).toLocaleString()}</span>
                  </p>
                  <p className="pilot-info">
                    <span className="label">Pilot:</span>
                    <span className="value">{flight.pilot?.name || 'Unassigned'}</span>
                  </p>
                </div>
              </div>
            ))}
          </div>
        </section>
        
        <section className="pilots-section">
          <div className="section-header">
            <h2>Available Pilots</h2>
            <span className="pilot-count">{pilots.length} Pilots</span>
          </div>
          <div className="pilot-list">
            {pilots.map(pilot => (
              <div key={pilot.id} className="pilot-card">
                <div className="pilot-header">
                  <h3>{pilot.name}</h3>
                  <span className="hours">
                    {pilot.hoursLeftThisWeek}h available
                  </span>
                </div>
                <div className="pilot-status">
                  {pilot.hoursLeftThisWeek > 0 ? 'Available' : 'Off Duty'}
                </div>
              </div>
            ))}
          </div>
        </section>
      </main>
    </div>
  );
};

export default Dashboard;