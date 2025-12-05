import { Move } from '@/lib/chessEngine';
import { ScrollArea } from '@/components/ui/scroll-area';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';

interface MoveHistoryProps {
  moves: Move[];
}

export function MoveHistory({ moves }: MoveHistoryProps) {
  const movePairs: { number: number; white?: string; black?: string }[] = [];
  
  for (let i = 0; i < moves.length; i += 2) {
    movePairs.push({
      number: Math.floor(i / 2) + 1,
      white: moves[i]?.notation,
      black: moves[i + 1]?.notation,
    });
  }

  return (
    <Card className="bg-white/[0.03] border-white/10 h-full">
      <CardHeader className="pb-2">
        <CardTitle className="text-base font-semibold text-white/90 flex items-center gap-2">
          <span className="w-2 h-2 rounded-full chess-gradient-blue" />
          Move History
        </CardTitle>
      </CardHeader>
      <CardContent className="p-0">
        <ScrollArea className="h-48 lg:h-64 px-4" data-testid="move-history">
          {movePairs.length === 0 ? (
            <p className="text-sm text-white/40 py-4 text-center">No moves yet</p>
          ) : (
            <div className="space-y-1 pb-4">
              {movePairs.map((pair, index) => (
                <div 
                  key={pair.number}
                  className={`
                    flex items-center gap-2 text-sm py-1 px-2 rounded
                    ${index === movePairs.length - 1 ? 'bg-white/5' : ''}
                  `}
                  data-testid={`move-${pair.number}`}
                >
                  <span className="text-white/40 w-6 text-right font-mono text-xs">
                    {pair.number}.
                  </span>
                  <span className="text-white/90 w-16 font-mono">
                    {pair.white || '...'}
                  </span>
                  <span className="text-white/70 w-16 font-mono">
                    {pair.black || ''}
                  </span>
                </div>
              ))}
            </div>
          )}
        </ScrollArea>
      </CardContent>
    </Card>
  );
}
