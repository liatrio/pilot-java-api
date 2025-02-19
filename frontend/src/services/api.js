const API_BASE_URL = '/api';

export const FlightService = {
    getAllFlights: async () => {
        try {
            const response = await fetch(`${API_BASE_URL}/flights`);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return await response.json();
        } catch (error) {
            console.error('Error fetching flights:', error);
            throw error;
        }
    },

    addFlight: async (flight) => {
        try {
            const response = await fetch(`${API_BASE_URL}/flights`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(flight)
            });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return await response.json();
        } catch (error) {
            console.error('Error adding flight:', error);
            throw error;
        }
    }
};

export const PilotService = {
    getAllPilots: async () => {
        try {
            const response = await fetch(`${API_BASE_URL}/pilots`);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return await response.json();
        } catch (error) {
            console.error('Error fetching pilots:', error);
            throw error;
        }
    },

    addPilot: async (pilot) => {
        try {
            const response = await fetch(`${API_BASE_URL}/pilots`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(pilot)
            });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return await response.json();
        } catch (error) {
            console.error('Error adding pilot:', error);
            throw error;
        }
    }
};