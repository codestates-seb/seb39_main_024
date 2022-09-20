import { ReactNode } from 'react';

type ChildrenComponent = {
  children: ReactNode;
};

export default function Layout(props: ChildrenComponent) {
  return (
    <main className="flex justify-center items-center h-full">
      <div className=""></div>
      <section className="sm:max-w-480 border-solid border-2 border-indigo-600">
        {props.children}
      </section>
      <div className=""></div>
    </main>
  );
}
