import Button from './Button';

export default function Modal({ className, onMenu }) {
  return (
    <div
      className={`flex flex-col fixed w-40 h-full mt-[64px] bg-pink opacity-90 text-xl z-[1] ${className}`}
    >
      <Button link="/videos" str="운동하기" onClick={onMenu} />
      <Button link="/posts" str="공유하기" onClick={onMenu} />
    </div>
  );
}
