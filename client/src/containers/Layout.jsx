export default function Layout({ children, path }) {
  return (
    <main
      className={`flex justify-center items-center ${
        path.includes('/mypage') ? 'bg-split-green-pink' : ''
      }
      ${
        path.includes('/mypage') ||
        path.includes('/login') ||
        path.includes('/join') ||
        path.includes('/posts')
          ? // 화면 스크롤 안 생기게 메인 박스 높이를 vh에 맞춤
            'sm:min-h-[calc(100vh-80px)] min-h-[calc(100vh-80px)] pt-[64px] md:pt-[80px]'
          : // 메인 박스 높이를 자식 요소 높이에 맞춤
            'pt-[102px] md:pt-[126px]'
      }
      `}
    >
      <section className="sm:max-w-480 w-768">{children}</section>
    </main>
  );
}
