import React, { useState, useEffect } from 'react';
import './FlightList.css';

const FlightList = () => {
  const [flights, setFlights] = useState([]);

  useEffect(() => {
    fetch('/api/flights')
      .then(res => res.json())
      .then(data => setFlights(data))
      .catch(error => console.error('Error fetching flights:', error));
  }, []);

  return (
    <div className="flight-list-container">
      <h2>Scheduled Flights</h2>
      <div className="flight-grid">
        {flights.map(flight => (
          <div key={flight.id} className="flight-item">
            <div className="flight-header">
              <span className="flight-number">{flight.flightNumber}</span>
              <span className="flight-status">Scheduled</span>
            </div>
            <div className="flight-route">
              <span>{flight.origin}</span>
              <span className="arrow">→</span>
              <span>{flight.destination}</span>
            </div>
            <div className="flight-times">
              <div>Departure: {new Date(flight.departureTime).toLocaleString()}</div>
              <div>Arrival: {new Date(flight.arrivalTime).toLocaleString()}</div>
            </div>
            <div className="flight-pilot">
              Pilot: {flight.pilot?.name || 'Unassigned'}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default FlightList;