import { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
// import { useRecoilValue } from 'recoil';

// import { imgReadState } from '../../recoil/selectors/imgReadState';
import Button from '../../components/Button';
import Summary from './Summary';

export default function MyPage(props) {
  const location = useLocation();

  const [path, setPath] = useState('');
  // const imgRead = useRecoilValue(imgReadState);

  useEffect(() => {
    setPath(location.pathname);
  }, [location]);

  return (
    <main className="border-solid border flex sm:flex-col">
      <section className="flex flex-col bg-green">
        <div className="flex flex-col sm:items-center">
          <img
            className="border-solid border border-zinc-300 p-2 w-48 h-48 sm:w-32 sm:h-32"
            src={'http://211.41.205.19:8080/members/1/image'}
            alt="img"
          />
          <p className="text-center mb-2.5">username</p>
        </div>
        <div className="sm:hidden flex flex-col items-center py-12 bg-pink">
          <Button link="/mypage" str="요약" />
          <Button link="/mypage/calendar" str="캘린더" />
          <Button link="/mypage/profile_edit" str="프로필 편집" />
          <Button link="/mypage/deleteaccount" str="회원 탈퇴" />
        </div>
      </section>
      <section className="flex flex-col justify-center w-full bg-pale_pink md:m-3">
        {path === '/mypage' ? <Summary /> : <>{props.children}</>}
      </section>
    </main>
  );
}
