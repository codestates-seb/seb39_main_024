import { useRecoilValue } from 'recoil';

import { memberImgState } from '../../recoil/selectors/memberImgState';

export default function ProfileIcon() {
  const memberImg = useRecoilValue(memberImgState);

  return (
    <img
      className="m-2 w-[30px] h-[30px] rounded-full"
      src={memberImg}
      alt="user"
    />
  );
}
