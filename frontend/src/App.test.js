import { render, screen } from '@testing-library/react';
import App from './App';

test('renders Flight Operations Center header', () => {
  render(<App />);
  const headerElement = screen.getByText(/Flight Operations Center/i);
  expect(headerElement).toBeInTheDocument();
});
