import { Link } from 'react-router-dom';

type Data = {
  className?: string;
  link?: string;
  str?: string;
  onClick?: () => void;
};

export default function Button(data: Data) {
  return (
    <Link className={data.className} to={`${data.link}`}>
      <button className="m-2" onClick={data.onClick}>
        {data.str}
      </button>
    </Link>
  );
}
