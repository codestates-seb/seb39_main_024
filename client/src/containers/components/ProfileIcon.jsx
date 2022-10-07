import { useRecoilValue } from 'recoil';
import { useNavigate } from 'react-router-dom';
import { memberImgState } from '../../recoil/selectors/memberImgState';

export default function ProfileIcon() {
  const memberImg = useRecoilValue(memberImgState);
  const navigation = useNavigate();

  if (memberImg === 'token') {
    alert('로그인 시간이 만료되었습니다. 다시 로그인 해주세요.');
    window.localStorage.clear();
    window.location.replace('/login');
  }

  return (
    <img
      className="m-2 w-[30px] h-[30px] rounded-full cursor-pointer"
      src={memberImg}
      alt="user"
      onClick={() => navigation('/mypage')}
      aria-hidden="true"
    />
  );
}
