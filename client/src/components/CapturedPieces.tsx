import { Piece, PieceColor, getPieceSymbol, getPieceValue } from '@/lib/chessEngine';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';

interface CapturedPiecesProps {
  capturedPieces: { white: Piece[]; black: Piece[] };
}

export function CapturedPieces({ capturedPieces }: CapturedPiecesProps) {
  const calculateMaterialAdvantage = () => {
    const whiteValue = capturedPieces.black.reduce((sum, p) => sum + getPieceValue(p.type), 0);
    const blackValue = capturedPieces.white.reduce((sum, p) => sum + getPieceValue(p.type), 0);
    return whiteValue - blackValue;
  };

  const advantage = calculateMaterialAdvantage();

  return (
    <Card className="bg-white/[0.03] border-white/10">
      <CardHeader className="pb-2">
        <CardTitle className="text-base font-semibold text-white/90 flex items-center gap-2">
          <span className="w-2 h-2 rounded-full chess-gradient-red" />
          Captured Pieces
        </CardTitle>
      </CardHeader>
      <CardContent className="space-y-3" data-testid="captured-pieces">
        <CapturedRow 
          label="White captured" 
          pieces={capturedPieces.black} 
          color="white"
          advantage={advantage > 0 ? advantage : undefined}
        />
        <CapturedRow 
          label="Black captured" 
          pieces={capturedPieces.white} 
          color="black"
          advantage={advantage < 0 ? -advantage : undefined}
        />
      </CardContent>
    </Card>
  );
}

interface CapturedRowProps {
  label: string;
  pieces: Piece[];
  color: PieceColor;
  advantage?: number;
}

function CapturedRow({ label, pieces, color, advantage }: CapturedRowProps) {
  const sortedPieces = [...pieces].sort((a, b) => getPieceValue(b.type) - getPieceValue(a.type));

  return (
    <div>
      <div className="flex items-center justify-between mb-1">
        <span className="text-xs text-white/50">{label}</span>
        {advantage !== undefined && advantage > 0 && (
          <span className={`
            text-xs font-medium
            ${color === 'white' ? 'text-[#4facfe]' : 'text-[#f5576c]'}
          `}>
            +{advantage}
          </span>
        )}
      </div>
      <div className="flex flex-wrap gap-0.5 min-h-[28px]">
        {sortedPieces.length === 0 ? (
          <span className="text-xs text-white/30">None</span>
        ) : (
          sortedPieces.map((piece, index) => (
            <span 
              key={index}
              className={`
                text-xl
                ${piece.color === 'white' 
                  ? 'text-white drop-shadow-[0_1px_2px_rgba(0,0,0,0.8)]' 
                  : 'text-gray-400 drop-shadow-[0_1px_2px_rgba(0,0,0,0.5)]'
                }
              `}
              data-testid={`captured-${piece.color}-${piece.type}-${index}`}
            >
              {getPieceSymbol(piece)}
            </span>
          ))
        )}
      </div>
    </div>
  );
}
