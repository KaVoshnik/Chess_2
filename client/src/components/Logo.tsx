interface LogoProps {
  size?: 'small' | 'normal' | 'large';
}

export function Logo({ size = 'normal' }: LogoProps) {
  const sizeClasses = {
    small: 'text-xl',
    normal: 'text-2xl',
    large: 'text-4xl sm:text-5xl',
  };

  return (
    <div className={`font-bold ${sizeClasses[size]} flex items-baseline gap-0`} data-testid="logo">
      <span className="chess-text-blue">Chess</span>
      <span className={`chess-text-red ${size === 'large' ? 'text-5xl sm:text-6xl' : size === 'normal' ? 'text-3xl' : 'text-2xl'}`}>
        2
      </span>
    </div>
  );
}
