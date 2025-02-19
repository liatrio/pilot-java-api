import { render, screen, waitFor } from '@testing-library/react';
import Dashboard from '../Dashboard';

describe('Dashboard', () => {
  beforeEach(() => {
    global.fetch = jest.fn();
  });

  test('renders loading state initially', () => {
    global.fetch.mockImplementation(() => new Promise(() => {}));
    render(<Dashboard />);
    expect(screen.getByText('Loading...')).toBeInTheDocument();
  });

  test('renders flights and pilots after successful data fetch', async () => {
    const mockData = {
      flights: [
        {
          id: 1,
          flightNumber: 'FL123',
          origin: 'JFK',
          destination: 'LAX',
          departureTime: '2024-03-20T10:00:00'
        }
      ],
      pilots: [
        {
          id: 1,
          name: 'John Doe',
          hoursLeftThisWeek: 40
        }
      ]
    };

    global.fetch.mockImplementation((url) => 
      Promise.resolve({
        json: () => Promise.resolve(
          url.includes('flights') ? mockData.flights : mockData.pilots
        )
      })
    );

    render(<Dashboard />);

    await waitFor(() => {
      expect(screen.getByText('FL123')).toBeInTheDocument();
      expect(screen.getByText('John Doe')).toBeInTheDocument();
    });
  });

  test('renders error state on fetch failure', async () => {
    global.fetch.mockRejectedValue(new Error('Failed to fetch'));
    
    render(<Dashboard />);
    
    await waitFor(() => {
      expect(screen.getByText('Failed to fetch data')).toBeInTheDocument();
    });
  });
});