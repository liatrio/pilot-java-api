import React, { useState, useEffect } from 'react';
import './PilotList.css';

const PilotList = () => {
  const [pilots, setPilots] = useState([]);

  useEffect(() => {
    fetch('/api/pilots')
      .then(res => res.json())
      .then(data => setPilots(data))
      .catch(error => console.error('Error fetching pilots:', error));
  }, []);

  return (
    <div className="pilot-list-container">
      <h2>Available Pilots</h2>
      <div className="pilot-grid">
        {pilots.map(pilot => (
          <div key={pilot.id} className="pilot-item">
            <div className="pilot-header">
              <span className="pilot-name">{pilot.name}</span>
              <span className="pilot-status">Active</span>
            </div>
            <div className="pilot-details">
              <div>Age: {pilot.age}</div>
              <div>License: Commercial</div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default PilotList;