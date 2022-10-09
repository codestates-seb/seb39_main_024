import { useRecoilValue } from 'recoil';
import { getMemberInfoState } from '../../../recoil/selectors/getMemberInfoState';
import running from '../../../images/running.png';
import jumpRope from '../../../images/jump-rope.png';
import dumbbel from '../../../images/dumbbel.png';
import medal from '../../../images/medal.png';

export default function Goal() {
  const totalRecord = useRecoilValue(getMemberInfoState).totalRecord;
  return (
    <section className="p-4 rounded-[8px] bg-light_pink grow">
      <p className="md:pb-4 font-extrabold">목표 달성 스티커</p>
      <div className="flex items-center w-full overflow-x-scroll">
        <img
          className={`m-[5px] w-[100px] ${
            totalRecord < 72000 ? 'grayscale' : ''
          }`}
          src={running}
          alt="running"
        />
        <img
          className={`m-[5px] w-[100px] ${
            totalRecord < 180000 ? 'grayscale' : ''
          }`}
          src={jumpRope}
          alt="jump rope"
        />
        <img
          className={`m-[5px] w-[100px] ${
            totalRecord < 288000 ? 'grayscale' : ''
          }`}
          src={dumbbel}
          alt="dumbbel"
        />
        <img
          className={`m-[5px] w-[100px] ${
            totalRecord < 360000 ? 'grayscale' : ''
          }`}
          src={medal}
          alt="medal"
        />
      </div>
    </section>
  );
}
