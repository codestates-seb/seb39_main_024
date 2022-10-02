import { useSetRecoilState } from 'recoil';
import { categoryIdState } from '../recoil/atoms/categoryIdState';
import Search from '../components/Search';
import Button from '../components/Button';

export default function Navbar({ path }) {
  const setCategoryId = useSetRecoilState(categoryIdState);

  const navHandler = (e) => {
    setCategoryId(Number(e.target.value));
  };

  return (
    <nav
      className={`flex fixed mt-[64px] md:mt-[80px] h-10 md:h-12 sm:text-xs bg-white border-y-[1px]`}
    >
      {path.includes('/videos') && (
        <div className="flex flex-row items-center justify-between w-screen">
          <div>
            <Button link="/videos" str="조회수 Top 10" />
            <Button link="/videos/training" str="홈트레이닝" />
            <Button link="/videos/stretching" str="스트레칭" />
          </div>
          <div>
            <Search placeholder="영상 검색하기" />
          </div>
        </div>
      )}
      {path.includes('/posts') && (
        <div className="flex flex-row items-center justify-between w-screen">
          <div>
            <Button link="/posts" str="전체" />
            <Button
              link="/posts/record"
              str="운동 기록"
              value="1"
              onClick={navHandler}
            />
            <Button
              link="/posts/meal"
              str="다이어트 식단"
              value="2"
              onClick={navHandler}
            />
            <Button
              link="/posts/free"
              str="자유"
              value="3"
              onClick={navHandler}
            />
          </div>
          <div>
            <Button link="/posts/create" str="글쓰기" />
            <input placeholder="글 검색하기" />
          </div>
        </div>
      )}
    </nav>
  );
}
