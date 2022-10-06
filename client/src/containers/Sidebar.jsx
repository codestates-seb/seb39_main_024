import { useRecoilValue } from 'recoil';
import { getMemberInfoState } from '../recoil/selectors/getMemberInfoState';
import { memberImgState } from '../recoil/selectors/memberImgState';
import Button from './components/Button';

export default function Sidebar() {
  const memberInfo = useRecoilValue(getMemberInfoState);
  const memberImg = useRecoilValue(memberImgState);
  return (
    <aside className="flex flex-col justify-center items-center w-[300px]">
      <div className="flex flex-col items-center ">
        <img
          className="border-solid border border-zinc-300 w-48 h-48 sm:w-32 sm:h-32"
          src={memberImg}
          alt="img"
        />
        <p className="text-center text-2xl mb-2.5">{memberInfo.name}</p>
      </div>
      <div className="sm:hidden flex flex-col items-center py-12">
        <Button link="/mypage" str="요약" />
        <Button link="/mypage/calendar" str="캘린더" />
        <Button link="/mypage/profile-edit" str="프로필 편집" />
        <Button link="/mypage/delete-account" str="회원 탈퇴" />
      </div>
    </aside>
  );
}
