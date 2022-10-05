import { useLocation } from 'react-router-dom';
import Videos from './Videos';

export default function SearchResult() {
  const location = useLocation();
  const query = location.state.value;
  return <Videos query={query} />;
}
