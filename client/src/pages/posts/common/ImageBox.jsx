import { useState } from 'react';
import { useRecoilValue } from 'recoil';
import { imgReadState } from '../../../recoil/selectors/imgReadState';

export default function ImageBox({ postRead }) {
  const [imgPage, setImgPage] = useState(0);
  const imgUrl = useRecoilValue(imgReadState(postRead.imageId[imgPage]));
  return (
    <section className="w-full flex justify-center py-5">
      <button
        className="sm:text-xl text-4xl pr-2 hover:cursor-pointer hover:bg-slate-200/30 disabled:bg-white disabled:cursor-auto"
        disabled={imgPage < 1}
        onClick={() => setImgPage((prev) => prev - 1)}
      >
        &#8249;
      </button>
      <img
        className="text-center border-solid border border-zinc-300 rounded-sm sm:w-[300px] sm:h-[300px] md:w-[650px] md:h-[650px]"
        src={imgUrl}
        alt="img"
      />
      <button
        className="sm:text-xl text-4xl pl-2 hover:cursor-pointer hover:bg-slate-200/30 disabled:bg-white disabled:cursor-auto"
        disabled={postRead.imageId.length === imgPage + 1}
        onClick={() => setImgPage((prev) => prev + 1)}
      >
        &#8250;
      </button>
    </section>
  );
}
