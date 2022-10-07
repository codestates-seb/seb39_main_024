import { useRecoilValue } from 'recoil';
import { getMemberInfoState } from '../recoil/selectors/getMemberInfoState';
import { memberImgState } from '../recoil/selectors/memberImgState';
import Button from './components/Button';

export default function Sidebar() {
  const memberInfo = useRecoilValue(getMemberInfoState);
  const memberImg = useRecoilValue(memberImgState);
  return (
    <aside className="flex flex-col sm:justify-center sm:items-center md:w-[400px] sm:w-full sm:mt-2">
      <div className="flex flex-col justify-center items-center md:pt-[10%] md:pb-[10%] h-[40%] sm:w-1/3">
        <img
          className="max-w-[60%] rounded-full mb-[8px] md:mb-[15px]"
          src={memberImg}
          alt="img"
        />
        <p className="text-center text-2xl">{memberInfo.name}</p>
      </div>
      <div className="flex md:flex-col sm:justify-center md:items-center h-[60%] sm:mt-2 md:mt-[20%] text-xl md:text-2xl bg-pink w-full">
        <Button link="/mypage" str="요약" />
        <Button link="/mypage/calendar" str="캘린더" />
        <Button link="/mypage/profile-edit" str="프로필 편집" />
        <Button link="/mypage/delete-account" str="회원 탈퇴" />
      </div>
    </aside>
  );
}
