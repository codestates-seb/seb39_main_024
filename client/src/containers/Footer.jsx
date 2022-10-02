export default function Footer({ path }) {
  return (
    <footer
      className={`flex justify-center items-center h-20	 bottom-0 ${
        path.includes('/mypage') ? 'bg-pink' : 'bg-green'
      }`}
    >
      ©2022. Fly away. All rights reserved.
    </footer>
  );
}
