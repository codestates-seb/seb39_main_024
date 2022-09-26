import Button from './Button';

export default function Modal(props) {
  return (
    <div
      className={`flex flex-col fixed w-40 h-full bg-red-100 text-xl ${props.className}`}
    >
      <Button link="/videos" str="운동하기" onClick={props.onMenu} />
      <Button link="/posts" str="공유하기" onClick={props.onMenu} />
    </div>
  );
}
