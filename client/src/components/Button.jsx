import { Link } from 'react-router-dom';

export default function Button(data) {
  return (
    <Link className={data.className} to={`${data.link}`}>
      <button className="m-2" value={data.value} onClick={data.onClick}>
        {data.str}
      </button>
    </Link>
  );
}
