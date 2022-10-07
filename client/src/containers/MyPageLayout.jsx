import { useEffect } from 'react';
import Sidebar from './Sidebar';

export default function MyPage({ children }) {
  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);
  return (
    <div className="flex sm:flex-col sm:min-h-[calc(100vh-80px)] min-h-[calc(100vh-80px)] md:bg-split-green-pink bg-sm-split-green-pink sm:pt-[64px] md:pt-[80px]">
      <Sidebar />
      <main className="flex flex-col justify-center items-center w-full sm:pt-5 bg-[#f9f9f9] md:mr-[60px] md:rounded-xl">
        {children}
      </main>
    </div>
  );
}
