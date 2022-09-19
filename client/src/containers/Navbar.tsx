import Button from '../components/Button';

export default function Navbar() {
  return (
    <nav className="flex sm:text-xs">
      <Button link="/all" str="전체" />
      <Button link="/views" str="조회수 Top 10" />
      <Button link="/popularity" str="인기 Top 10" />
      <Button link="/training" str="홈트레이닝" />
      <Button link="/stretching" str="스트레칭" />
    </nav>
  );
}
