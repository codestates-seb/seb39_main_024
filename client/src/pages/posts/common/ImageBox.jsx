import { useState } from 'react';
import { useRecoilValue } from 'recoil';
import { imgReadState } from '../../../recoil/selectors/imgReadState';

export default function ImageBox({ postRead }) {
  const [imgPage, setImgPage] = useState(0);
  const imgUrl = useRecoilValue(imgReadState(postRead.imageId[imgPage]));
  return (
    <section className="w-full flex justify-center py-5">
      <button
        className="text-5xl px-2 hover:cursor-pointer hover:bg-slate-200/30 disabled:bg-white disabled:cursor-auto"
        disabled={imgPage < 1}
        onClick={() => setImgPage((prev) => prev - 1)}
      >
        &#8249;
      </button>
      <img
        className="text-center border-solid border border-zinc-300 rounded-sm sm:w-[358px] sm:h-[358px] md:w-[654px] md:h-[654px]"
        src={imgUrl}
        alt="img"
      />
      <button
        className="text-5xl px-2 hover:cursor-pointer hover:bg-slate-200/30 disabled:bg-white disabled:cursor-auto"
        disabled={postRead.imageId.length === imgPage + 1}
        onClick={() => setImgPage((prev) => prev + 1)}
      >
        &#8250;
      </button>
    </section>
  );
}
