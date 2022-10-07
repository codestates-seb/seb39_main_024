import { useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import SearchIcon from '../../images/SearchIcon';

export default function Search({ placeholder }) {
  const navigate = useNavigate();
  const inputRef = useRef();

  const handleSearch = () => {
    const value = inputRef.current.value;
    navigate('/videos/search-result', { state: { value: value } });
  };

  const onClick = () => {
    handleSearch();
  };

  const onKeyPress = (e) => {
    if (e.key === 'Enter') {
      handleSearch();
    }
  };

  return (
    <div className="flex border-b -translate-x-5">
      <input
        ref={inputRef}
        className="pt-1.5 w-full h-full outline-none"
        type="search"
        placeholder={placeholder}
        onKeyPress={onKeyPress}
      />
      <button type="submit" onClick={onClick}>
        <SearchIcon />
      </button>
    </div>
  );
}
